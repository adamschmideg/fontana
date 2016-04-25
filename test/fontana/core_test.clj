(ns fontana.core-test
  (:require [clojure.test :refer :all]
            [instaparse.core :as insta]
            [fontana.core :refer :all]))

(defn parse-from [script start]
  (insta/transform 
    {:Line str,
     :Multiline vector
     }
    (fountain-parser script :start start)))

(deftest title-page 
  (are [script parsed]
       (= (parse-from script :TitlePage) parsed)
       "Title: Casablanca"
       [:TitlePage [:Title "Casablanca"]]

       "Title:\n\tJack\n\tand\n\tJill"
       [:TitlePage [:Title ["Jack" "and" "Jill"]]]

       "Draft date: 01/April/1999"
       [:TitlePage [:DraftDate "01/April/1999"]]

       "Something: Foo bar"
       [:TitlePage [:OtherPart "Something" "Foo bar"]]

       "Something else: Foo bar"
       [:TitlePage [:OtherPart "Something else" "Foo bar"]]

       "Title: Foo bar\nAuthor: John Doe"
       [:TitlePage 
          [:Title "Foo bar"]
          [:Author "John Doe"]]
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
