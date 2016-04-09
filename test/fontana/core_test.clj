(ns fontana.core-test
  (:require [clojure.test :refer :all]
            [fontana.core :refer :all]))

(deftest dialog
  (testing "one"
    (is (=
          (parse "JOE\nHey, Jane")
          [:Script
            [:Dialogue
              [:Character "JOE"]
              [:Talk "Hey, Jane"]]])))
(testing "more"
    (is (=
          (parse
"JOE
Hey

JANE
What?")
          [:Script
            [:Dialogue
              [:Character "JOE"]
              [:Talk "Hey"]]
            [:Dialogue
              [:Character "JANE"]
              [:Talk "What?"]]]))))


(run-tests)
