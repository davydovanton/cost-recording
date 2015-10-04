(ns cost-recording.core
  (:use cost-recording.text-parsing)
  (:require [compojure.core :refer :all]
            [org.httpkit.server :refer [run-server]]))

(import java.io.StringWriter)
(use '[clojure.pprint :only (pprint)])

(def month (slurp "resources/test.txt"))

(defn hashmap-to-string [m] 
  (let [w (StringWriter.)] (pprint m w)(.toString w)))

(defroutes myapp
  (GET "/" [] (hashmap-to-string (parse month))))

(defn -main []
  (run-server myapp {:port 5000}))
