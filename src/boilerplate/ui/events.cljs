(ns boilerplate.ui.events
  (:require
    [day8.re-frame.http-fx]
    [ajax.core :as ajax]
    [re-frame.core :as rf]))

(rf/reg-event-db
  :on-query-success
  (fn [db [_ resource result]] ;TODO: also check if status 200 else handle errors
    (-> db
        (assoc resource (get result :data))
        (assoc :loading? false)
        (assoc :fetching-error nil))))

(rf/reg-event-db
  :on-query-fail
  (fn [db [_ resource result]]
    (-> db
        (assoc :fetching-error
        	(str "Error fetching " (name resource) ": "
        		 (:message (:response result))))
        (assoc :loading? false))))

(rf/reg-event-db
  :on-post-success
  (fn [db [_ resource result]]
    (-> db
        (assoc resource (get result :data))
        (assoc :processing? false)
        (assoc :processing-error nil))))

(rf/reg-event-db
  :on-post-fail
  (fn [db [_ resource result]]
    (-> db
        (assoc :processing-error
        	(str "Error: "
        		 (:message (:response result))))
        (assoc :processing? false))))

(defn fetch [{:keys [db]} [_ endpoint resource]]
  {:db (assoc db :loading? true)
   :http-xhrio {:method          :get
                :uri             (str js/window.location.origin endpoint)
                :timeout         8000
                :response-format (ajax/json-response-format {:keywords? true})
                :on-success      [:on-query-success resource]
                :on-failure      [:on-query-fail resource]}})


(defn post [{:keys [db]} [_ endpoint resource data]]
  {:db (assoc (assoc db :processing? true) (keyword resource) (resource data))
   :http-xhrio {:method          :post
                :uri             (str js/window.location.origin endpoint)
                :params          data
                :timeout         300000
                :format          (ajax/json-request-format)
                :response-format (ajax/json-response-format {:keywords? true})
                :on-success      [:on-post-success resource]
                :on-failure      [:on-post-fail resource]}})

(rf/reg-event-fx :fetch fetch)
(rf/reg-event-fx :post post)