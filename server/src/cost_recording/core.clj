(ns cost-recording.core
  (:use cost-recording.text-parsing)
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware.json :as middleware]
            [ring.middleware.cors :refer [wrap-cors]]
            [org.httpkit.server :refer [run-server]]))

(def month (slurp "resources/test.txt"))

(defroutes app-routes
  (GET "/api/index.json" []
       {:body {:hello "world"}})

  (GET "/api/costs.json" []
       {:body (cost-parse month)})

  (route/resources "/")
  (route/not-found {:body "error"}))

(def app
  (-> (handler/api app-routes)
      (wrap-cors :access-control-allow-origin [#".*"]
                 :access-control-allow-methods [:get :put :post :delete])
      (middleware/wrap-json-body)
      (middleware/wrap-json-params)
      (middleware/wrap-json-response)))

(defn -main []
  (run-server app {:port 8081}))
