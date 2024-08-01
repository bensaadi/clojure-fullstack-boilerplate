(ns boilerplate.server
  (:require 
    [compojure.core :refer [defroutes context GET]]
    [compojure.route :as route]
    [ring.middleware.reload :refer [wrap-reload]] 
    [ring.middleware.content-type :refer [wrap-content-type]]
    [ring.middleware.json :refer [wrap-json-response wrap-json-body]]
    [ring.util.response :as r]
    [ring.adapter.jetty :as jetty]
    [clojure.string :as s]

    [boilerplate.api.foo :as foo]
    [boilerplate.api.utils :refer [wrap-datomic]]
    [boilerplate.api.db :refer [setup-db db-url]])
  (:gen-class))

(def api-routes
  (context "/" []
    (context "/api" []
      (context "/foo" [] (foo/controller))
      (route/not-found {:body {:status "error" :message "Not found"}}))))
 
(defroutes api-handler
  (-> api-routes (wrap-datomic db-url) wrap-json-response wrap-json-body wrap-reload))

(defn handler [request]
  (if (s/starts-with? (:uri request) "/api")
    (api-handler request)
    (if (s/starts-with? (:uri request) "/public")
     ((wrap-content-type (constantly (r/file-response (str "resources" (:uri request))))) request)
     (r/file-response "resources/public/index.html")
    )))

(defn -main
  [& args]
  (defonce server
    (do
      (println "Web server listening on port 3000")
      (setup-db)
      (jetty/run-jetty handler {:port 3000}))))