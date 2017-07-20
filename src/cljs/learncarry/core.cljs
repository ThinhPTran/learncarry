(ns learncarry.core
  (:require [reagent.core :as r]
            [carry.core :as carry]
            [carry-reagent.core :as carry-reagent]
            [learncarry.counter :as counter]))

(enable-console-print!)

(defn main
  []
  (let [app (carry/app counter/blueprint)
        [app-view-model app-view] (carry-reagent/connect app counter/view-model counter/view)]
    (r/render app-view (.getElementById js/document "root"))
    ((:dispatch-signal app) :on-start)
    (assoc app :view-model app-view-model)))

(def app (main))

;;;;;;;;;;;;;;;;;;;;;;;; Figwheel stuff
(defn before-jsload
  []
  ((:dispatch-signal app) :on-stop))

(defn on-jsload
  []
  #_(. js/console clear))

