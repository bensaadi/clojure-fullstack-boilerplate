(ns boilerplate.ui.components.layout
  (:require
    [re-frame.core :as rf]
    [boilerplate.ui.components.nav :refer [nav]]))

(rf/reg-sub :loading? (fn [db] (:loading? db)))
(rf/reg-sub :fetching-error (fn [db] (:fetching-error db)))
(rf/reg-sub :processing-error (fn [db] (:processing-error db)))

(defn layout
  "Dashboard layout"
  [_props & children]
  (let [loading? @(rf/subscribe [:loading?])
        processing-error @(rf/subscribe [:processing-error])
        fetching-error @(rf/subscribe [:fetching-error])]
    [:div {:class "dashboard"}
     (when loading? [:div {:class "loading-indicator"} "Loading..."])
     (when processing-error [:div {:class "error-message"} processing-error])
     (when fetching-error [:div {:class "error-message"} fetching-error])
     [nav]
     [:main
      [:div {:class "content"}
       [:div children]]]]))
