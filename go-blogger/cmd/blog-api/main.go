package main

import (
	"log"
	"net/http"
	"os"
)

func main() {
	args := os.Args

	port := args[1]

	mux := ApiRoutes()

	log.Printf("Listening on %s", port)
	err := http.ListenAndServe(":"+port, mux)
	log.Fatal(err)
}
