package main

import (
	"database/sql"
	"encoding/json"
	"htmx-example/pkg"
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
	db, err := sql.Open("sqlite3", "./stuff.db")
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

	mux.HandleFunc("/stuff/item", crudItems)
	mux.HandleFunc("/stuff/item/search", searchItemByTitle)
	mux.HandleFunc("/stuff/item/active", countActiveItems)
	mux.HandleFunc("/stuff/item/active/toggle", toggleActiveById)

	return mux
}

func apiIndex(w http.ResponseWriter, r *http.Request) {

	if r.URL.Path != "/" {
		w.WriteHeader(http.StatusNotFound)
		return
	}
}

func crudItems(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json")

	switch r.Method {
	case http.MethodGet:

		kv := r.URL.Query()
		skip, err := strconv.Atoi(kv.Get("skip"))
		if err != nil {
			skip = 0
		}

		take, err := strconv.Atoi(kv.Get("take"))

		if err != nil {
			take = 10
		}
		json.NewEncoder(w).Encode(getItems(skip, take))

	case http.MethodPost:

		var item pkg.Item
		err := json.NewDecoder(r.Body).Decode(&item)

		if err != nil {
			log.Default().Fatalf("%s %s:%d", err.Error(), file, line)
		}

		err = createItem(item.Title, item.Active)

		if err != nil {
			http.Error(w, "Internal Server Error", http.StatusInternalServerError)
		}

	default:
		println("default")
		w.Header().Set("Allow", "GET, POST, OPTIONS")
		http.Error(w, "method not allowed", http.StatusMethodNotAllowed)
	}

}

func searchItemByTitle(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json")

	if r.Method == http.MethodGet {

		kv := r.URL.Query()
		searchString := kv.Get("search-string")
		var items []pkg.Item

		if searchString != "" {
			rows, err := container.db.Query("SELECT * FROM item WHERE title LIKE ?", "%"+searchString+"%")

			if err != nil {
				log.Default().Fatalf("%s %s:%d", err.Error(), file, line)
			}

			for rows.Next() {
				item := new(pkg.Item)
				err = rows.Scan(&item.Id, &item.Title, &item.Active)

				if err != nil {
					log.Default().Fatalf("%s %s:%d", err.Error(), file, line)
				}

				items = append(items, *item)
			}

			json.NewEncoder(w).Encode(items)
		} else {
			json.NewEncoder(w).Encode(getItems(0, 10))
		}
	} else {
		w.Header().Set("Allow", "GET, POST, OPTIONS")
		http.Error(w, "method not allowed", http.StatusMethodNotAllowed)
	}

}

func countActiveItems(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json")

	if r.Method == http.MethodGet {

		var count int

		err := container.db.QueryRow("SELECT COUNT(*) FROM item WHERE Active = 1").Scan(&count)

		if err != nil {
			log.Default().Fatalf("%s %s:%d", err.Error(), file, line)
			http.Error(w, "internal server error", http.StatusInternalServerError)
		} else {
			json.NewEncoder(w).Encode(struct{ Count int }{count})
		}

	} else {
		w.Header().Set("Allow", "GET, POST, OPTIONS")
		http.Error(w, "method not allowed", http.StatusMethodNotAllowed)
	}

}

func toggleActiveById(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json")

	if r.Method == http.MethodPost {
		id := struct{ Id int }{}
		err := json.NewDecoder(r.Body).Decode(&id)

		if err != nil {
			log.Default().Fatalf("%s %s:%d", err.Error(), file, line)
		}

		stmt, err := container.db.Prepare("UPDATE item SET Active =  Active <> 1 WHERE id = ?")

		if err != nil {
			log.Default().Fatalf("%s %s:%d", err.Error(), file, line)
			http.Error(w, "internal server error", http.StatusInternalServerError)
		}

		_, err = stmt.Exec(id.Id)

		if err != nil {
			log.Default().Fatalf("%s %s:%d", err.Error(), file, line)
			http.Error(w, "internal server error", http.StatusInternalServerError)
		}

	} else {
		w.Header().Set("Allow", "GET, POST, OPTIONS")
		http.Error(w, "method not allowed", http.StatusMethodNotAllowed)
	}

}

func createItem(title string, active bool) error {
	stmt, err := container.db.Prepare("INSERT INTO item (title, active) VALUES (?, ?)")

	if err != nil {
		log.Default().Fatalf("%s %s:%d", err.Error(), file, line)
	}

	_, err = stmt.Exec(title, active)

	return err
}

func getItems(skip int, take int) []pkg.Item {
	rows, err := container.db.Query("SELECT id, title, active FROM item LIMIT ? OFFSET ?", take, skip*take)

	if err != nil {
		log.Default().Fatalf("%s %s:%d", err.Error(), file, line)
	}

	var items []pkg.Item

	for rows.Next() {
		item := new(pkg.Item)
		err = rows.Scan(&item.Id, &item.Title, &item.Active)

		if err != nil {
			log.Default().Fatalf("%s %s:%d", err.Error(), file, line)
		}

		items = append(items, *item)
	}

	return items
}
