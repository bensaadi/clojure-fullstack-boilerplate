![alt text](https://github.com/bensaadi/clojure-fullstack-boilerplate/blob/main/resources/public/img/clojure-fullstack-boilerplate.svg?raw=true)

This boilerplate lets you quickly get started using Clojure, ClojureScript and Datomic, while providing a smooth transition for developers already familiar with a React/Node.JS stack. 

### Get started
This boilerplate requires connecting to Datomic.

Datomic Pro is free and you can launch a local development server like so:
```
curl https://datomic-pro-downloads.s3.amazonaws.com/1.0.7180/datomic-pro-1.0.7180.zip -O
unzip datomic-pro-1.0.7180.zip && cd datomic-pro-1.0.7180
bin/transactor config/samples/dev-transactor-template.properties 
```
To start a development server with hot-reloading:

```
lein fig:build
```
To compile Sass in development:
```
sass --watch src/boilerplate/ui/sass/:resources/public/css/
```
To build a production front-end bundle:
```
lein fig:min
```
To build a production .jar back-end:
```
lein uberjar
```

Connect your favorite text editor to REPL on port 5555.


### Conversion dictionary 
For those already familiar with React/Node.JS, below is a dictionary of libraries used in this boilerplate.

| JavaScript                       | Clojure/ClojureScript |
| -------------------------------- | --------------------- |
| Node's http + Express middleware | Ring                  |
| Express routing                  | Compojure             |
| React                            | Reagent               |
| Redux                            | Re-frame              |
| React Router                     | Reitit + Accountant   |
| Fetch API                        | Re-frame's http-fx    |


### Contributors

This project is created and currently maintained by [L. Bensaadi](https://bensaadi.com/).
