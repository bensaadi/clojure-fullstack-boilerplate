(ns boilerplate.api.db
 	(:require
  	 [datomic.api :as d]
  	 [datomic-schema.schema :as s]
  	 [boilerplate.api.utils :refer :all]
  	 [boilerplate.api.schema :refer [db-parts db-schema]]))

(def db-url "datomic:dev://localhost:4334/boilerplate")

(defn setup-db []
  (d/delete-database db-url)
  (d/create-database db-url)
  (d/transact
    (d/connect db-url)
   	(concat
     	(s/generate-parts db-parts)
     	(s/generate-schema db-schema))))
