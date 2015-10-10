(ns cost-recording.pdf-generation
  (require [clojure.string :as str])
  (:use clj-pdf.core))

(defn format-day [day]
  (format "%7s - %s" (:cost day) (:description day)))

(defn make-paragraph-by-day [date data]
  (identity
    (into []
          (concat [[:paragraph date]]
                  (map #(identity [:paragraph {:encoding :unicode} (format-day %)])
                       data)))))

(defn make-paragraph [data]
  (reduce into
          (map #(make-paragraph-by-day (first %) (second %))
               (group-by :date data))))

(defn total-text [data]
  (let [total-cost (reduce + (map #(read-string (:cost %)) data))]
    (format "\n--------------------\nTotal: %7d" total-cost)))

(defn total [data]
  [:paragraph {:encoding :unicode} (total-text data)])

(defn generate-pdf [data]
  (pdf
    [{:font {:encoding :unicode}}
      (make-paragraph data)
      (total data)]
    "doc.pdf"))
