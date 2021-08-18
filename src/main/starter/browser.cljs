(ns starter.browser
  (:require
    [clojure.pprint :refer [pprint]]

    [reagent.core :as r]
    [reagent.dom :as rdom]

    [starter.sci :as sci]))

(defn code-eval [source]
  [:div
   {:style {:width 500
            :border "1px solid black"
            :margin 10
            :padding 10}}
   [:pre [:code source]]
   [:hr]
   (let [res (sci/eval-string source)]
     (if (:result res)
       [:pre (prn-str (:result (sci/eval-string source)))]
       [:pre
        {:style {:color "red"
                 :white-space "break-spaces"}}
        (prn-str (:error (sci/eval-string source)))]))])

(defn $app []
  [:div
    [code-eval
     "(ratom {:count 0})"]
    [code-eval
     "(with-let1 [t 10] [:div t])"]
    [code-eval
     "(reagent.core/with-let2 [t 10] [:div t])"]
    [code-eval
     "(with-let3 [t 10] [:div t])"]
    [code-eval
     "(with-let4 [t 10] [:div t])"]])

(defn ^:dev/after-load start []
  (js/console.log (str "Starting " (js/Date.)))
  (rdom/render [$app] (js/document.getElementById "app")))

(defn init []
  (start))

(defn ^:dev/before-load stop []
  (js/console.clear))
