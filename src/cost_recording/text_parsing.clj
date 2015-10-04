(ns cost-recording.text-parsing
  (require [clojure.string :as str]))

(defn split-by-day [month] (str/split month #"\n\n"))

(defn create-cost-hash [date text]
  (let [data (str/split text #"\s-\s")]
    (hash-map :date date :cost (first data) :description (last data))))

(defn costs [day] 
  (let [lines (str/split day #"\n")
        date (first lines)]
    (map #(create-cost-hash date %) (rest lines))))

(defn parse [text]
  (map #(costs %) (split-by-day text)))
