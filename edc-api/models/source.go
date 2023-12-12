package models

type DataStructure struct {
	Name   string
	Inputs []Input
}

type Input struct {
	Name        string
	DataType    string `yaml:"data-type"`
	Value       string
	Format      string
	Validations string
}
