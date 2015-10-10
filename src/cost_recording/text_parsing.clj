(ns cost-recording.text-parsing
  (require [clojure.string :as str]))

(defn split-by-day [month] (str/split month #"\n\n"))

(defn create-cost-hash [date text]
  (let [data (str/split text #"\s-\s")]
    (hash-map :date date :cost (first data) :description (last data))))

(defn costs [day] 
  (let [lines (str/split day #"\n")
        date (first lines)]
    (mapv #(create-cost-hash date %) (rest lines))))

(defn cost-parse [text]
    (vec (flatten
        (mapv #(costs %) (split-by-day text)))))
