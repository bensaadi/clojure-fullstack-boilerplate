(ns boilerplate.api.foo
  (:require 
    [boilerplate.api.utils :refer :all]
    [compojure.core :refer [routes GET]]
    [datomic.api :as d]))

(defn get-foos [_request]
  (success (format-query-output [{ :foo "bar" }])))

(defn controller []
  (routes
    (GET "/" [] get-foos)))