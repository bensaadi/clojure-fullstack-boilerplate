(ns boilerplate.ui.components.sample-page
  (:require
    [reagent.core :as r]
    [re-frame.core :as rf]))

(rf/reg-sub :foo (fn [db] (:foo db)))

(defn sample-page []
  (let [fetch-foo #(rf/dispatch [:fetch "/api/foo" :foo])
        foo (rf/subscribe [:foo])]
    (r/create-class
      {:display-name "sample-page"
       :component-did-mount fetch-foo
       :reagent-render
       (fn []
         [:div
          [:h1 "Sample page"]
          [:p "I'm a sample page created to demonstrate routing and API GET."]
          [:p (str "Received : " @foo)]])})))


