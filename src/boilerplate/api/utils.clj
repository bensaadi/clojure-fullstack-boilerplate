(ns boilerplate.api.utils
  (:require 
    [clojure.string :as s]
    [clojure.walk :refer [postwalk]]
    [datomic.api :as d]))

; JSend specs

(defn success [data]
  {:body {:status "success" :data data}})

(defn fail [message]
  {:status 400 :body {:status "fail" :message message}})

(defn error [message]
  {:status 500 :body {:status "error" :message message}})


; formatting

(defn strip-namespaces [d]
  (postwalk #(if (keyword? %) (keyword (name %)) %) d))

(defn format-query-output [output]
  (->>
    output
    (postwalk (fn [item] (get item :db/ident item))) ; simplify enums
    strip-namespaces)) ; strip namespaces


; db-related

(def ^{:dynamic true :doc "A Datomic database value used over the life of a Ring request."} *db*)
(def ^{:dynamic true :doc "A Datomic connection bound for the life of a Ring request."} *connection*)

(defn wrap-datomic
  "A Ring middleware that provides a request-consistent database connection and
value for the life of a request."
  [handler uri]
  (fn [request]
    (let [conn (d/connect uri)]
      (binding [*connection* conn
                *db*         (d/db conn)]
        (handler request)))))

(defn transact
  "Run a transaction with a request-consistent connection."
  [tx]
  (d/transact *connection* tx))

(defn q
  "Runs the given query over a request-consistent database as well as
the other given sources."
  [query & sources]
  (apply d/q query *db* sources))

