# Using HTMX

## Goal

Create a simple crud front-end. I am not sure for what yet. Use HTMX to create the front-end with tailwinds.css

The server will be a golang server (http only) not using a library. The pages will be using html/templates.

/\*
func handleStopSignals() {
s := make(chan os.Signal, 10)
signal.Notify(s, os.Interrupt, syscall.SIGTERM)

    for range s {
    	log.Printf("Is %v doing anything?\n", s)
    	os.Exit(0)
    }

}

\*/

/\*

func getCreatePostPage(w http.ResponseWriter, r \*http.Request) {
if r.Method == http.MethodGet {
var templatFile = "base.tmpl.html"

    	files := []string{
    		"./web/templates/base.tmpl.html",
    		"./web/templates/create-post.tmpl.html",
    		"./web/templates/nav.tmpl.html",
    		"./web/templates/404.html",
    	}

    	templates, err := template.ParseFiles(files...)
    	if err != nil {
    		panic(err)
    	}

    	err = templates.ExecuteTemplate(w, templatFile, nil)

    	if err != nil {
    		panic(err)
    	}
    } else {
    	log.Printf("Bad Request made to getCreatePage on %s", r.Method)
    	http.Error(w, "Method not allowed", http.StatusMethodNotAllowed)
    }

}



func getPostById(w http.ResponseWriter, r \*http.Request) {
if r.Method == http.MethodGet {
kv := r.URL.Query()
id := kv.Get("id")

    	post := services.FindPostById(container.db, id)

    	var templatFile = "base.tmpl.html"

    	files := []string{
    		"./web/templates/base.tmpl.html",
    		"./web/templates/reader.tmpl.html",
    		"./web/templates/nav.tmpl.html",
    		"./templates/404.html",
    	}

    	templates, err := template.ParseFiles(files...)
    	if err != nil {
    		panic(err)
    	}

    	err = templates.ExecuteTemplate(w, templatFile, post)

    	if err != nil {
    		panic(err)
    	}

    } else {
    	log.Printf("Bad Request made to getCreatePage on %s", r.Method)
    	http.Error(w, "Method not allowed", http.StatusMethodNotAllowed)
    }

}

func createPost(w http.ResponseWriter, r \*http.Request) {
if r.Method == http.MethodPost {

    	r.ParseForm()

    	services.CreatePost(container.db, r.Form.Get("title"), r.Form.Get("content"), r.Form.Get("tags"))

    	var templatString = "<h1>Testing</h1>"
    	templates, err := template.New("Banner").Parse(templatString)

    	if err != nil {
    		log.Fatal(err)
    	}

    	err = templates.ExecuteTemplate(w, "Banner", nil)
    	if err != nil {
    		log.Fatal(err)
    	}
    } else {
    	log.Printf("Bad Request made to getCreatePage on %s", r.Method)
    	http.Error(w, "Method not allowed", http.StatusMethodNotAllowed)
    }

}

func getProfilePage(w http.ResponseWriter, r \*http.Request) {
if r.Method == http.MethodGet {
var templatFile = "base.tmpl.html"
files := []string{
"./web/templates/base.tmpl.html",
"./web/templates/profile.tmpl.html",
"./web/templates/nav.tmpl.html",
"./web/templates/404.html",
}

    	templates, err := template.ParseFiles(files...)

    	if err != nil {
    		panic(err)
    	}

    	err = templates.ExecuteTemplate(w, templatFile, nil)

    	if err != nil {
    		panic(err)
    	}
    } else {
    	log.Printf("Bad Request made to getPostPage on %s", r.Method)
    	http.Error(w, "Method not allowed", http.StatusMethodNotAllowed)
    }

}
\*/
