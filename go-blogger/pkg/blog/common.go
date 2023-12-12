package pkg

import "time"

type Post struct {
	Id           int
	Title        string
	Content      string
	Tags         string
	Created_date time.Time
	Update_date  time.Time
}
