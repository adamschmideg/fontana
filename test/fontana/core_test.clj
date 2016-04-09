(ns fontana.core-test
  (:require [clojure.test :refer :all]
            [fontana.core :refer :all]))

(deftest dialog
  (testing "one"
    (is (=
          (parse "
EXT. HOUSE - DAY

JOE
Hey, Jane")
          [:Script
	    [:Scene 
              [:Heading "EXT." " HOUSE - DAY"]
              [:Dialogue
                [:Character "JOE"]
                [:Talk "Hey, Jane"]]]]))))


(run-tests)
