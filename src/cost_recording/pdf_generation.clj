(ns cost-recording.pdf-generation
  (require [clojure.string :as str])
  (:use clj-pdf.core))

(defn format-day [day]
  (format "%7s - %s" (:cost day) (:description day)))

(defn make-paragraph-by-day [date data]
  (identity
    (into []
          (concat [[:paragraph date]]
                  (map #(identity [:paragraph (format-day %)])
                       data)))))

(defn make-paragraph [data]
  (reduce into
          (map #(make-paragraph-by-day (first %) (second %))
               (group-by :date data))))

(defn total-spelling [data]
  (reduce + (map #(read-string (:cost %)) data)))

(defn month-stat [data]
  (map #(identity [(first %) (total-spelling (second %))])
       (group-by :date data)))

(defn month-chart [data]
  [:chart
   {:type :line-chart
    :time-series true
    :time-format "dd.MM"
    :title "Line Chart"
    :x-label "data"
    :y-label "spending"}
    (into []
          (concat ["spending"] (month-stat data)))])


(defn total-text [data]
  (format "\n--------------------\nTotal: %7d rubs" (total-spelling data)))

(defn total [data]
  [:paragraph (total-text data)])

(defn generate-pdf [data]
  (pdf
    [{:font {:encoding "UniJIS-UCS2-H"
       :ttf-name "HeiseiKakuGo-W5"}}
      (make-paragraph data)
      (month-chart data)
      (total data)]
    "doc.pdf"))
