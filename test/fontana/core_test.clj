(ns fontana.core-test
  (:require [clojure.test :refer :all]
            [instaparse.core :as insta]
            [fontana.core :refer :all]))

(defn parse-part [script]
  (first
    (insta/transform 
      {:Line str}
      (fountain-parser script :start :Part))))

(deftest dialog 
  (are [script parsed]
       (= (parse-part script) parsed)
       "JOE\nCome here"
       [:Dialogue
        [:Character "JOE"]
        [:Talk "Come here"]]

       "JOE\n(impatient)\nCome here"
       [:Dialogue
        [:Character "JOE"]
        [:Parenthetical "impatient"]
        [:Talk "Come here"]]
       ))

(run-tests)
