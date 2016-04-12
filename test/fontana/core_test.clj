(ns fontana.core-test
  (:require [clojure.test :refer :all]
            [fontana.core :refer :all]))

(def script "
EXT. HOUSE - DAY

JOE holds a KNIFE in his hand.
He looks tired.

JANE comes and notices Joe.

JOE
Hey

JANE
(angrily)
What?
You called me?

JOE
Relax

They enter the house.

INT. ROOM - DAY

Joe switches on the light.
")

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


(clojure.pprint/pprint (parse script))

;(run-tests)
