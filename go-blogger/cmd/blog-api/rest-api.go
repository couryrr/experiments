package main

import (
	pkg "couryrr-blog/pkg/blog"
	"database/sql"
	"encoding/json"
	"log"
	"net/http"
	"runtime"
	"strconv"

	_ "github.com/mattn/go-sqlite3"
)

type Container struct {
	db *sql.DB
}

var container *Container
var file string
var line int

func init() {
	container = &Container{}
	db, err := sql.Open("sqlite3", "./blog.db")
	if err != nil {
		log.Default().Fatalf("%s %s:%d", err.Error(), file, line)
	}
	container.db = db

	_, f, l, _ := runtime.Caller(0)
	file = f
	line = l
}

func ApiRoutes() *http.ServeMux {

	mux := http.NewServeMux()

	//Default route handling
	mux.HandleFunc("/", apiIndex)

	mux.HandleFunc("/blog/post", crudPost)
	mux.HandleFunc("/blog/post/search", searchPostByTitle)

	return mux
}

func apiIndex(w http.ResponseWriter, r *http.Request) {

	if r.URL.Path != "/" {
		w.WriteHeader(http.StatusNotFound)
		return
	}
}

func crudPost(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json")

	switch r.Method {
	case http.MethodGet:

		kv := r.URL.Query()
		id := kv.Get("id")
		var posts []pkg.Post

		if id != "" {
			posts = append(posts, findPostById(id))
			json.NewEncoder(w).Encode(posts)
		} else {
			skip, err := strconv.Atoi(kv.Get("skip"))
			if err != nil {
				skip = 0
			}

			take, err := strconv.Atoi(kv.Get("take"))

			if err != nil {
				take = 10
			}
			json.NewEncoder(w).Encode(getPosts(skip, take))
		}

	case http.MethodPost:
		var post pkg.Post
		err := json.NewDecoder(r.Body).Decode(&post)

		if err != nil {
			log.Default().Fatalf("%s %s:%d", err.Error(), file, line)
		}

		err = createPost(post.Title, post.Content, post.Tags)

		if err != nil {
			http.Error(w, "Internal Server Error", http.StatusInternalServerError)
		}

	default:
		println("default")
		w.Header().Set("Allow", "GET, POST, OPTIONS")
		http.Error(w, "method not allowed", http.StatusMethodNotAllowed)
	}

}

func searchPostByTitle(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json")

	if r.Method == http.MethodGet {

		kv := r.URL.Query()
		searchString := kv.Get("search-string")
		var posts []pkg.Post

		if searchString != "" {
			rows, err := container.db.Query("SELECT * FROM post WHERE title LIKE ?", "%"+searchString+"%")

			if err != nil {
				log.Default().Fatalf("%s %s:%d", err.Error(), file, line)
			}

			for rows.Next() {
				post := new(pkg.Post)
				err = rows.Scan(&post.Id, &post.Title, &post.Content, &post.Tags, &post.Created_date, &post.Update_date)

				if err != nil {
					log.Default().Fatalf("%s %s:%d", err.Error(), file, line)
				}

				posts = append(posts, *post)
			}

			json.NewEncoder(w).Encode(posts)
		} else {
			json.NewEncoder(w).Encode(getPosts(0, 10))
		}
	} else {
		w.Header().Set("Allow", "GET, POST, OPTIONS")
		http.Error(w, "method not allowed", http.StatusMethodNotAllowed)
	}

}

func createPost(title string, content string, tags string) error {
	stmt, err := container.db.Prepare("INSERT INTO post (title, content, tag, created_date, updated_date) VALUES (?, ?, ?, datetime('now'), datetime('now'))")

	if err != nil {
		log.Default().Fatalf("%s %s:%d", err.Error(), file, line)
	}

	_, err = stmt.Exec(title, content, tags)

	return err
}

func findPostById(id string) (post pkg.Post) {
	row := container.db.QueryRow("SELECT * FROM post WHERE id = ?", id)
	err := row.Scan(&post.Id, &post.Title, &post.Content, &post.Tags, &post.Created_date, &post.Update_date)

	if err != nil {
		log.Default().Fatalf("%s %s:%d", err.Error(), file, line)
	}

	return post

}

func getPosts(skip int, take int) []pkg.Post {
	rows, err := container.db.Query("SELECT * FROM post ORDER BY created_date LIMIT ? OFFSET ?", take, skip*take)

	if err != nil {
		log.Default().Fatalf("%s %s:%d", err.Error(), file, line)
	}

	defer rows.Close()

	var posts []pkg.Post

	for rows.Next() {
		post := new(pkg.Post)
		err = rows.Scan(&post.Id, &post.Title, &post.Content, &post.Tags, &post.Created_date, &post.Update_date)

		if err != nil {
			log.Default().Fatalf("%s %s:%d", err.Error(), file, line)
		}

		posts = append(posts, *post)
	}

	return posts
}
