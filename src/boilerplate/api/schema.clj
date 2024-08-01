(ns boilerplate.api.schema
  (:require
    [datomic-schema.schema :as s]))

(def db-parts [(s/part "boilerplate")])

; define your schema using datomic-schema

(def db-schema
  [(s/schema example-users
     (s/fields
       [first-name       :string]
       [last-name        :string]))])

