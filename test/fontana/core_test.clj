(ns fontana.core-test
  (:require [clojure.test :refer :all]
            [instaparse.core :as insta]
            [fontana.core :refer :all]))

(defn parse-from [script start]
  (insta/transform 
    {:Line str}
    (fountain-parser script :start start)))

(deftest title-page 
  (are [script parsed]
       (= (parse-from script :TitlePage) parsed)
       "Title: Casablanca"
       [:TitlePage [:Title "Casablanca"]]
       ))

(deftest dialog 
  (are [script parsed]
       (= (parse-from script :Part) parsed)
       "JOE\nCome here"
       '([:Dialogue
          [:Character "JOE"]
          [:Talk "Come here"]])

       "JOE\n(impatient)\nCome here"
       '([:Dialogue
          [:Character "JOE"]
          [:Parenthetical "impatient"]
          [:Talk "Come here"]])
       ))

(run-tests)
