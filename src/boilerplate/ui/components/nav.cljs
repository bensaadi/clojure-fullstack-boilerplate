(ns boilerplate.ui.components.nav
  (:require
    [reitit.frontend :as reitit]
    [boilerplate.ui.router :refer [router]]
    [reagent.session :as session]))

(defn path-for [route]
  (:path (reitit/match-by-name router route)))

(defn nav-link
  [route label]
  (let [current-route (:current-route (session/get :route))]
    [:li
     [:a {:class (when (= current-route route) "current")
          :href (path-for route)} label ]]))

(defn nav
  "Dashboard navigation"  
  [props]
  [:header
   [:div {:class "nav-wrapper"}
    [:nav
     [:a.brand {:href "/"} [:img {:src "/public/img/brand.svg"}]]
     [:ul {:class "nav-ul"}
      [nav-link :index "Index"]
      [nav-link :sample-page "Sample page"]]]]])


