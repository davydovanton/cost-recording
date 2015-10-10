(ns cost-recording.core
  (:require [clojure.tools.cli :refer [cli]])
  (:use cost-recording.pdf-generation)
  (:use cost-recording.text-parsing))

(defn text [args]
  (let [default-file "resources/test.txt"]
    (slurp
      (or (first args) default-file)
      :encoding "UTF-8")))

(defn -main [& args]
  (generate-pdf
    (cost-parse (text args))))
