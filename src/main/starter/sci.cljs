(ns starter.sci
  (:require
    [sci.core :as sci]
    [reagent.core :as r]
    [clojure.pprint :refer [pprint]])
  (:require-macros
    [starter.macros :as macros]))
  ;; (:require-macros
  ;;   [reagent.ratom :refer [with-let]]))

(def with-let1 ^:sci/macro
  (fn [_ _ bindings & body]
    `(r/with-let ~bindings ~@body)))

(def with-let3 ^:sci/macro
  (fn [_ _ bindings & body]
    `(macros/with-let3 ~bindings ~@body)))

(def with-let4 ^:sci/macro
  (fn [_ _ bindings & body]
    `(macros/with-let4 ~bindings ~@body)))

(def rns (sci/create-ns 'reagent.core nil))

(def context
  (sci/init
    {:namespaces {'user {'ratom r/atom
                         'with-let1 with-let1
                         'with-let3 with-let3
                         'with-let4 with-let4}
                  'reagent.core {'with-let2 (sci/copy-var with-let1 rns)}}}))

(defn eval-string [source]
  (try
   {:source source
    :result (sci/eval-string* context source)}
   (catch js/Error e
     {:source source
      :error (str e)})))
