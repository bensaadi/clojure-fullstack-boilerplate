(ns boilerplate.ui.router
 	(:require
    [reitit.frontend :as reitit]))

(def router
  (reitit/router
    [["/" :index]
     ["/sample-page" :sample-page]]))