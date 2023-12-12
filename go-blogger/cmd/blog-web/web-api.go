package main

import (
	"bytes"
	pkg "couryrr-blog/pkg/blog"
	"encoding/json"
	"html/template"
	"io"
	"log"
	"net/http"
	"runtime"

	"github.com/stripe/stripe-go/v74"
	"github.com/stripe/stripe-go/v74/checkout/session"
)

var host string
var file string
var line int

func init() {
	_, f, l, _ := runtime.Caller(0)
	file = f
	line = l
	host = "http://localhost:9000"

	// This is your test secret API key.
	stripe.Key = ""

}

func WebRoutes() *http.ServeMux {

	mux := http.NewServeMux()

	fs := http.FileServer(http.Dir("./web/blog/static"))
	mux.Handle("/static/", http.StripPrefix("/static/", fs))

	mux.HandleFunc("/", webIndex)

	mux.HandleFunc("/blog/post/create", blogPostCreatePage)
	mux.HandleFunc("/blog/post", blogPostCreate)

	mux.HandleFunc("/blog", blogFeedView)
	mux.HandleFunc("/blog/post/view", blogPostView)
	mux.HandleFunc("/blog/post/search", blogPostSearch)

	mux.HandleFunc("/message", messageMe)

	mux.HandleFunc("/store", storePage)
	mux.HandleFunc("/store/checkout", checkOut)
	mux.HandleFunc("/store/checkout/success", storeCheckoutSuccess)
	mux.HandleFunc("/store/checkout/cancel", storeCheckoutCancel)

	return mux
}

func webIndex(w http.ResponseWriter, r *http.Request) {

	if r.URL.Path != "/" {
		w.WriteHeader(http.StatusNotFound)
		return
	}

	if r.Method == http.MethodGet {
		template := template.Must(template.ParseFiles("./web/blog/pages/index.html"))
		template.Execute(w, nil)

	} else {
		w.WriteHeader(http.StatusMethodNotAllowed)
		return
	}
}

func blogPostCreatePage(w http.ResponseWriter, r *http.Request) {

	if r.Method == http.MethodGet {
		template := template.Must(template.ParseFiles("./web/blog/pages/create-post.html"))
		template.Execute(w, nil)
	} else {
		w.WriteHeader(http.StatusMethodNotAllowed)
		return
	}

}

func blogPostCreate(w http.ResponseWriter, r *http.Request) {

	if r.Method == http.MethodPost {
		url := host + "/blog/post"

		var post pkg.Post
		r.ParseForm()

		post.Title = r.Form.Get("title")
		post.Content = r.Form.Get("content")

		postBuffer := new(bytes.Buffer)

		err := json.NewEncoder(postBuffer).Encode(post)
		if err != nil {
			log.Default().Printf("%s %s:%d", err.Error(), file, line)
		}

		resp, err := http.Post(url, "application/json", postBuffer)
		if err != nil {
			log.Default().Printf("%s %s:%d", err.Error(), file, line)
		}
		if resp.StatusCode != http.StatusOK {
			log.Default().Println("Post not created")
		}

		template := template.Must(template.ParseFiles("./web/blog/components/parts.html"))
		template.ExecuteTemplate(w, "form-message", nil)
	} else {
		w.WriteHeader(http.StatusMethodNotAllowed)
		return
	}

}

func blogPostView(w http.ResponseWriter, r *http.Request) {
	if r.Method == http.MethodGet {
		var posts []pkg.Post
		id := r.URL.Query().Get("id")

		url := host + "/blog/post?id=" + id

		resp, err := http.Get(url)

		if err != nil {
			log.Default().Printf("%s %s:%d", err.Error(), file, line)
		}

		body, err := io.ReadAll(resp.Body)
		defer resp.Body.Close()

		if err != nil {
			log.Default().Printf("%s %s:%d", err.Error(), file, line)
		}

		err = json.Unmarshal(body, &posts)

		if err != nil {
			log.Default().Printf("%s %s:%d", err.Error(), file, line)
		}

		template := template.Must(template.ParseFiles("./web/blog/pages/view-post.html"))
		template.Execute(w, posts[0])
	} else {
		w.WriteHeader(http.StatusMethodNotAllowed)
		return
	}
}

func blogPostSearch(w http.ResponseWriter, r *http.Request) {
	if r.Method == http.MethodGet {

		var posts []pkg.Post
		searchString := r.URL.Query().Get("search-string")

		url := host + "/blog/post/search?search-string=" + searchString

		resp, err := http.Get(url)

		if err != nil {
			log.Default().Printf("%s %s:%d", err.Error(), file, line)
		}

		body, err := io.ReadAll(resp.Body)
		defer resp.Body.Close()

		if err != nil {
			log.Default().Printf("%s %s:%d", err.Error(), file, line)
		}

		err = json.Unmarshal(body, &posts)

		if err != nil {
			log.Default().Printf("%s %s:%d", err.Error(), file, line)
		}

		template := template.Must(template.ParseFiles("./web/blog/components/parts.html"))
		template.ExecuteTemplate(w, "post-list", posts)
	} else {
		w.WriteHeader(http.StatusMethodNotAllowed)
		return
	}
}

func blogFeedView(w http.ResponseWriter, r *http.Request) {
	if r.Method == http.MethodGet {
		var posts []pkg.Post

		url := host + "/blog/post"

		resp, err := http.Get(url)

		if err != nil {
			log.Default().Printf("%s %s:%d", err.Error(), file, line)
		}

		body, err := io.ReadAll(resp.Body)
		defer resp.Body.Close()

		if err != nil {
			log.Default().Printf("%s %s:%d", err.Error(), file, line)
		}

		err = json.Unmarshal(body, &posts)

		if err != nil {
			log.Default().Printf("%s %s:%d", err.Error(), file, line)
		}

		template := template.Must(template.ParseFiles("./web/blog/pages/blog-feed.html", "./web/blog/components/parts.html"))
		template.Execute(w, posts)
	} else {
		w.WriteHeader(http.StatusMethodNotAllowed)
		return
	}
}

func messageMe(w http.ResponseWriter, r *http.Request) {
	if r.Method == http.MethodGet {
		template := template.Must(template.ParseFiles("./web/blog/pages/message.html"))
		template.Execute(w, nil)
	} else {
		w.WriteHeader(http.StatusMethodNotAllowed)
		return
	}
}

func storePage(w http.ResponseWriter, r *http.Request) {
	if r.Method == http.MethodGet {

		template := template.Must(template.ParseFiles("./web/blog/pages/store.html", "./web/blog/components/parts.html"))
		template.Execute(w, nil)
	} else {
		w.WriteHeader(http.StatusMethodNotAllowed)
		return
	}
}

func storeCheckoutSuccess(w http.ResponseWriter, r *http.Request) {
	if r.Method == http.MethodGet {
		template := template.Must(template.ParseFiles("./web/blog/pages/store.html", "./web/blog/components/parts.html"))
		template.Execute(w, nil)
	} else {
		w.WriteHeader(http.StatusMethodNotAllowed)
		return
	}
}

func storeCheckoutCancel(w http.ResponseWriter, r *http.Request) {
	if r.Method == http.MethodGet {

		template := template.Must(template.ParseFiles("./web/blog/pages/store.html", "./web/blog/components/parts.html"))
		template.Execute(w, nil)
	} else {
		w.WriteHeader(http.StatusMethodNotAllowed)
		return
	}
}

func checkOut(w http.ResponseWriter, r *http.Request) {

	params := &stripe.CheckoutSessionParams{
		LineItems: []*stripe.CheckoutSessionLineItemParams{
			{
				Price:    stripe.String("price_1NdjiyJ2YBlI03IJ35r1rPZN"),
				Quantity: stripe.Int64(1),
			},
		},
		Mode:       stripe.String(string(stripe.CheckoutSessionModePayment)),
		SuccessURL: stripe.String("http://localhost:4242/store/checkout/success"),
		CancelURL:  stripe.String("http://localhost:4242/store/checkout/cancel"),
	}

	s, err := session.New(params)

	if err != nil {
		log.Printf("session.New: %v", err)
	}

	log.Default().Printf("%s %s:%d", s.URL, file, line)

	w.Header().Add("HX-Redirect", s.URL)
	w.WriteHeader(http.StatusSeeOther)
}
