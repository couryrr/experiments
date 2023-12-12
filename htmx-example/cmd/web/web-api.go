package main

import (
	"bytes"
	"encoding/json"
	"html/template"
	"htmx-example/pkg"
	"io"
	"log"
	"net/http"
	"runtime"
	"strconv"
	"strings"
)

var host string
var file string
var line int

func init() {
	_, f, l, _ := runtime.Caller(0)
	file = f
	line = l
	host = "http://localhost:9000"
}

func WebRoutes() *http.ServeMux {

	mux := http.NewServeMux()

	fs := http.FileServer(http.Dir("./web/example/static"))
	mux.Handle("/static/", http.StripPrefix("/static/", fs))

	mux.HandleFunc("/", feedView)
	mux.HandleFunc("/stuff/item/search", itemSearch)
	mux.HandleFunc("/stuff/item", itemCreate)
	mux.HandleFunc("/stuff/item/create", itemCreatePage)
	mux.HandleFunc("/stuff/item/active", activeItemCount)
	mux.HandleFunc("/stuff/item/toggle/active", toggleActiveItem)

	return mux
}

func itemCreatePage(w http.ResponseWriter, r *http.Request) {

	if r.Method == http.MethodGet {
		template := template.Must(template.ParseFiles("./web/example/components/parts.html"))
		template.ExecuteTemplate(w, "create-form", nil)
	} else {
		w.WriteHeader(http.StatusMethodNotAllowed)
		return
	}

}

func itemCreate(w http.ResponseWriter, r *http.Request) {

	if r.Method == http.MethodPost {
		url := host + "/stuff/item"

		var item pkg.Item
		r.ParseForm()

		item.Title = r.Form.Get("title")
		item.Active = true

		itemBuffer := new(bytes.Buffer)

		err := json.NewEncoder(itemBuffer).Encode(item)
		if err != nil {
			log.Default().Printf("%s %s:%d", err.Error(), file, line)
		}

		resp, err := http.Post(url, "application/json", itemBuffer)

		if err != nil {
			log.Default().Printf("%s %s:%d", err.Error(), file, line)
		}

		if resp.StatusCode != http.StatusOK {
			log.Default().Println("Item not created")
		}

		template := template.Must(template.ParseFiles("./web/example/components/parts.html"))
		template.ExecuteTemplate(w, "form-message", nil)
	} else {
		w.WriteHeader(http.StatusMethodNotAllowed)
		return
	}

}

func itemSearch(w http.ResponseWriter, r *http.Request) {
	if r.Method == http.MethodGet {

		var items []pkg.Item
		searchString := r.URL.Query().Get("search-string")

		url := host + "/stuff/item/search?search-string=" + searchString

		resp, err := http.Get(url)

		if err != nil {
			log.Default().Printf("%s %s:%d", err.Error(), file, line)
		}

		body, err := io.ReadAll(resp.Body)
		defer resp.Body.Close()

		if err != nil {
			log.Default().Printf("%s %s:%d", err.Error(), file, line)
		}

		err = json.Unmarshal(body, &items)

		if err != nil {
			log.Default().Printf("%s %s:%d", err.Error(), file, line)
		}

		template := template.Must(template.ParseFiles("./web/example/components/parts.html"))
		template.ExecuteTemplate(w, "item-list", items)
	} else {
		w.WriteHeader(http.StatusMethodNotAllowed)
		return
	}
}

func toggleActiveItem(w http.ResponseWriter, r *http.Request) {
	if r.Method == http.MethodPatch {
		v := r.Header.Get("Hx-Trigger-Name")
		id, err := strconv.Atoi(v[strings.Index(v, "-")+1:])
		if err != nil {
			log.Default().Printf("%s %s:%d", err.Error(), file, line)
		}
		url := host + "/stuff/item/active/toggle"

		itemBuffer := new(bytes.Buffer)

		err = json.NewEncoder(itemBuffer).Encode(struct{ Id int }{Id: id})
		if err != nil {
			log.Default().Printf("%s %s:%d", err.Error(), file, line)
		}

		resp, err := http.Post(url, "application/json", itemBuffer)

		if err != nil {
			log.Default().Printf("%s %s:%d", err.Error(), file, line)
		}

		if resp.StatusCode != http.StatusOK {
			log.Default().Println("Item not update")
		} else {
			w.Header().Add("HX-Trigger-After-Swap", "test-event-name")
		}

	} else {
		w.WriteHeader(http.StatusMethodNotAllowed)
		return
	}
}

func activeItemCount(w http.ResponseWriter, r *http.Request) {
	if r.Method == http.MethodGet {

		count := struct{ Count int }{}

		url := host + "/stuff/item/active"

		resp, err := http.Get(url)

		if err != nil {
			log.Default().Printf("%s %s:%d", err.Error(), file, line)
		}

		body, err := io.ReadAll(resp.Body)
		defer resp.Body.Close()

		if err != nil {
			log.Default().Printf("%s %s:%d", err.Error(), file, line)
		}

		err = json.Unmarshal(body, &count)

		if err != nil {
			log.Default().Printf("%s %s:%d", err.Error(), file, line)
		}

		template := template.Must(template.ParseFiles("./web/example/components/parts.html"))
		template.ExecuteTemplate(w, "item-count", count)
	} else {
		w.WriteHeader(http.StatusMethodNotAllowed)
		return
	}
}

func feedView(w http.ResponseWriter, r *http.Request) {
	if r.Method == http.MethodGet {
		var items []pkg.Item

		url := host + "/stuff/item"

		resp, err := http.Get(url)

		if err != nil {
			log.Default().Printf("%s %s:%d", err.Error(), file, line)
		}

		body, err := io.ReadAll(resp.Body)
		defer resp.Body.Close()

		if err != nil {
			log.Default().Printf("%s %s:%d", err.Error(), file, line)
		}

		err = json.Unmarshal(body, &items)

		if err != nil {
			log.Default().Printf("%s %s:%d", err.Error(), file, line)
		}

		template := template.Must(template.ParseFiles("./web/example/pages/main-feed.html", "./web/example/components/parts.html"))
		template.Execute(w, items)
	} else {
		w.WriteHeader(http.StatusMethodNotAllowed)
		return
	}
}
