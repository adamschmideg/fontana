(ns fontana.core-test
  (:require [clojure.test :refer :all]
            [fontana.core :refer :all]))

(deftest dialog
  (testing "one"
    (is (=
          (parse "JOE\nHey, Jane")
          [:Script
            [:Dialog
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
            [:Dialog
              [:Character "JOE"]
              [:Talk "Hey"]]
            [:Dialog
              [:Character "JANE"]
              [:Talk "What?"]]]))))

(run-tests)
