(ns fontana.core
  (:require [instaparse.core :as insta]))

(def fountain-parser
  (insta/parser (clojure.java.io/resource "fountain.bnf")))

(defn parse [s] (fountain-parser s))
