package services

import (
	"edc-api/models"
	"encoding/csv"
	"fmt"
	"io/ioutil"
	"net/http"
	"os"
	"regexp"
	"strconv"
	"strings"
	"time"

	"github.com/gin-gonic/gin"
	"gopkg.in/yaml.v2"
)

func UploadFile(context *gin.Context) {
	r := context.Request
	w := context.Writer
	//  Ensure our file does not exceed 5MB
	r.Body = http.MaxBytesReader(w, r.Body, 5*1024*1024)

	file, handler, err := r.FormFile("file")

	// Capture any errors that may arise
	if err != nil {
		fmt.Fprintf(w, "Error getting the file")
		fmt.Println(err)
		return
	}

	defer file.Close()

	fmt.Printf("Uploaded file name: %+v\n", handler.Filename)
	fmt.Printf("Uploaded file size %+v\n", handler.Size)
	fmt.Printf("File mime type %+v\n", handler.Header)

	// Get the file content type and access the file extension
	fileType := strings.Split(handler.Header.Get("Content-Type"), "/")[1]

	// Create the temporary file name
	fileName := fmt.Sprintf("upload-*.%s", fileType)
	// Create a temporary file with a dir folder
	tempFile, err := ioutil.TempFile("data", fileName)

	if err != nil {
		fmt.Println(err)
	}

	defer tempFile.Close()

	fileBytes, err := ioutil.ReadAll(file)
	if err != nil {
		fmt.Println(err)
	}

	tempFile.Write(fileBytes)
	g, _ := os.Open(tempFile.Name())
	defer g.Close()
	csvReader := csv.NewReader(g)
	csvData, csvDataError := csvReader.ReadAll()

	if csvDataError != nil {
		fmt.Fprintf(w, csvDataError.Error())
	}

	var dataStructure models.DataStructure

	for csvDataIndex, line := range csvData {
		if csvDataIndex == 0 {
			for _, value := range line {
				dataStructure.Inputs = append(dataStructure.Inputs, models.Input{Name: value})
			}
		}
		for lineIndex, value := range line {
			input := &dataStructure.Inputs[lineIndex]

			//If this can be a float then it is a number.
			_, err := strconv.ParseFloat(value, 32)
			if err == nil {
				input.DataType = "number"
				input.Value = value
				continue
			}

			dateLayout := "20060102"
			_, err = time.Parse(dateLayout, clearString(value))

			if err == nil {
				input.DataType = "date"
				input.Value = value
				continue
			}

			datetimeLayout := "20060102150405"
			_, err = time.Parse(datetimeLayout, clearString(value))

			if err == nil {
				input.DataType = "datetime"
				input.Value = value
				continue
			}
			input.DataType = "string"
			input.Value = value
		}
	}

	yaml, yamlErr := yaml.Marshal(dataStructure)
	if yamlErr != nil {
		fmt.Println(yamlErr)
	}

	// Create the temporary file name
	fileName2 := fmt.Sprintf("upload-*.%s", "yaml")
	// Create a temporary file with a dir folder
	tempFile2, err := ioutil.TempFile("data", fileName2)

	if err != nil {
		fmt.Println(err)
	}

	defer tempFile2.Close()

	tempFile2.Write(yaml)
	context.IndentedJSON(http.StatusOK, dataStructure)

}

func clearString(str string) string {
	var nonAlphanumericRegex = regexp.MustCompile(`[^a-su-zA-SU-Z0-9]+`)
	return nonAlphanumericRegex.ReplaceAllString(str, "")
}
