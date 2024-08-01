(defproject boilerplate "0.1.0-SNAPSHOT"
 	:jvm-opts ["-Dclojure.server.repl={:port 5555 :accept clojure.core.server/repl}"]
  :description "Clojure Full-Stack Boilerplate"
  :url "https://bensaadi.com"
  :license {:name "MIT"}

  :min-lein-version "2.7.1"
  :main ^:skip-aot boilerplate.server
  :dependencies [
                 ; server-side
  															[org.clojure/clojure "1.11.1"]
  															[com.datomic/peer "1.0.7180"]
                 [datomic-schema "1.3.1"]
                 [ring/ring-devel "1.8.0"]
                 [ring/ring-json "0.5.1"]
                 [ring/ring-jetty-adapter "1.9.1"]
                 [compojure "1.7.1"]

                 ; client-side
                 [org.clojure/clojurescript "1.11.4"]
																	
                 [reagent-utils "0.3.8"]
                 [re-frame "1.4.3"]
                 [day8.re-frame/http-fx "0.2.4"]
                 [metosin/reitit "0.7.1"]
                 [venantius/accountant "0.2.5"]]

  :source-paths ["src"]
  :target-path "target/%s"
  :java-source-paths ["java"]
  :ring {:handler boilerplate.server/api-handler}

  :aliases {"fig:build" ["trampoline" "run" "-m" "figwheel.main" "-b" "dev" "-r"]
            "fig:min"   ["run" "-m" "figwheel.main" "-O" "advanced" "-bo" "dev"]
            "fig:test"  ["run" "-m" "figwheel.main" "-co" "test.cljs.edn" "-m" "boilerplate.test-runner"]}

  :profiles {:dev {:dependencies [[com.bhauman/figwheel-main "0.2.17"]
                                  [org.slf4j/slf4j-nop "1.7.30"]
                                  [com.bhauman/rebel-readline-cljs "0.1.4"]
                                  [cljsjs/react "17.0.2-0"]
                 																	[cljsjs/react-dom "17.0.2-0"]
                                  [reagent "1.1.1"]]
                   :resource-paths ["target"]
                   ;; need to add the compiled assets to the :clean-targets
                   :clean-targets ^{:protect false} ["target"]}

             :prod {:dependencies [[reagent "1.1.1"  :exclusions [cljsjs/react cljsjs/react-dom cljsjs/react-dom-server]]]
                    :resource-paths ["target"]
                    ;; need to add the compiled assets to the :clean-targets
                    :clean-targets ^{:protect false} ["target"]}
             :uberjar {:aot :all}})

