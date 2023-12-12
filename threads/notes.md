# Java cli for "fun"

## Goals

- Run java without maven/spring or other fancy stuff.
- Create a threaded application

## Threadded application outline

Inital step is to call `GennyFromTheBlock` to create `n` json files.

The files will be a 0 - 255 list of random non-negative numbers. Values from 0 - 1,000,000
The thread will parse the json
Add the numbers
Create an entry in a Map of FileName -> sum
