package main

import (
	"edc-api/services"

	"github.com/gin-gonic/gin"
)

func main() {
	router := gin.Default()

	router.GET("/DataRequest", services.DataRequest)
	router.POST("/UploadCsv", services.UploadFile)

	router.Run("localhost:8081")
}
