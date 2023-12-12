# What are we doing?

https://htmx.org/attributes/hx-trigger/

visibity api

## Warning

There are some hot takes on the current state of web dev in here.

As a preface, I have never been a huge fan of SPAs and JavaScript ran clients. I was making website in PHP with JS (not jquery) in 2010. So I always really prefered server side templating with a sprinkle of JS. The world does seem to be getting back to this with SSR just in a very convoluted way.

## What is the point of htmx

To put it generally HTMX is an alternative to SPA for creating rich interactive web apps.

It removes pushing all of the code and state to the client.

Reduces the build of modern JS applications.

## SPA Developer Experience

We all know the meta SPA apps of Angular, NEXT.js (react is not for this anymore), Svelt, etc.

You need one of npm, npx, yarn, vite, etc.

Install a couple of the libraries CLI.

You pull in `node_modules`, setup your configs and run some version of `npm run dev` to start a local server and file watch.

The transpiler reruns every time you type a key.

## Maybe there is a better way

## Demo

### Noted limitation

Terrible error handling.
Not using built in transition from htmx
Very crudly designed (sorry trying to understand tailwinds)
Modal is not updating the table/count
I think there are better ways to handle the modal close but have not managed it yet.

### Cool idea from here

System warning (banner) based on events
There is the new (experimental) Visibility API that will make transitions to pages even better.

## Pros and Cons

- It is fairly light weight
- No complicated build step
- Gracfully falls back without JS (SPAS do not exist)

- Kind of black magic (what spa framework isn't)
- Each language has it own templating - fracturing UI
- Lots of extra attributes on your html
- XSS (stop it)
