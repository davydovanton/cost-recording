(defproject cost-recording "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :main ^:skip-aot cost-recording.core
  :target-path "target/%s"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [compojure "1.1.8"]
                 [http-kit "2.1.16"]
                 [ring/ring-core "1.3.2"]
                 [ring/ring-json "0.3.1"]
                 [ring-cors "0.1.7"]
                 [ring/ring-defaults "0.1.4"]]
  :ring {:handler cost-recording.core/app})
