package services

import (
	"edc-api/models"
	"fmt"
	"io/ioutil"
	"net/http"

	"github.com/gin-gonic/gin"
	"gopkg.in/yaml.v2"
)

func DataRequest(context *gin.Context) {
	fileData, ioErr := ioutil.ReadFile("..\\edc-api\\data\\example-data.yaml")

	if ioErr != nil {
		fmt.Println(ioErr.Error())
	}

	dataStructure := new(models.DataStructure)

	err := yaml.Unmarshal([]byte(fileData), &dataStructure)

	if err != nil {
		fmt.Println(ioErr.Error())
	}

	context.IndentedJSON(http.StatusOK, dataStructure)
}
