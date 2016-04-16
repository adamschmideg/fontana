(ns fontana.core
  (:require 
    [instaparse.core :as insta]
    [hiccup.core :as hiccup]))

(def fountain-parser
  (insta/parser (clojure.java.io/resource "fountain.bnf")))

(defn parse [s] 
  (->> (fountain-parser s) (insta/transform {:Line str})))

(defn join-lines [& x] (clojure.string/join "\n" x))

(defn div [clazz]
  (fn [& x] (into 
              [(keyword  (str "div." (name clazz)))] 
              x)))

(defn div-join-lines [clazz] (comp (div clazz) join-lines))

(defn to-html [tree]
  (let [script 
        (insta/transform 
            {:Line str,
             :Script (div :script),
             :Scene (div :scene),
             :Dialogue (div :dialogue),
             :Character (div :character),
             :Parenthetical (div-join-lines :parenthetical),
             :Talk (div-join-lines :talk),
             :Action (div-join-lines :action),
             :Header (div :header)}
            tree)]
    (hiccup/html 
      [:html 
       [:head
        [:link {:rel "stylesheet" :type "text/css" :href "screenplay.css"}]]
       [:body script]])))
  
(defn convert 
  ([in out]
    (spit out (convert in)))
  ([in]
    (-> in slurp parse to-html)))
        
