(ns cross-me
  (:require [clojure.math.combinatorics :as comb]))

(def not-nil? (complement nil?))

(defn first-ok? [row-possibility]
  (or
    (and (not-nil? (first row-possibility))
         (nil? (second row-possibility)))
    (nil? (first row-possibility))))

(defn last-ok? [row-possibility]
  (or
    (and (not-nil? (last row-possibility))
         (nil? (nth row-possibility (- (count row-possibility) 2))))
    (nil? (last row-possibility))))

(defn middles-ok? [row-possibility]
  (loop [row-possibility row-possibility
         i 1]
    (if (= i (dec (count row-possibility)))
      true
      (if (= false (or
                     (nil? (nth row-possibility i))
                     (and (nil? (nth row-possibility (inc i)))
                          (nil? (nth row-possibility (dec i))))))
        false
        (recur row-possibility (inc i))))))

(defn nils-between-segments? [row-possibility]
  (and (first-ok? row-possibility)
       (last-ok? row-possibility)
       (middles-ok? row-possibility)))

(defn segments-in-right-sequence? [row-possibility segments]
  (= segments (filter not-nil? row-possibility)))

(defn flatten-nested-vectors [vector-with-nests]
  (map #(flatten %) vector-with-nests))

(defn row-possibilities [row row-clue]
  (let [width (count row)
        filled-spaces (reduce + row-clue)
        segments (into [] (map #(into [] (repeat % "x")) row-clue))
        empty-spaces (into [] (repeat (- width filled-spaces) nil))]
    (flatten-nested-vectors
      (into []
      (filter #(and (nils-between-segments? %) (segments-in-right-sequence? % segments))
        (into [] (comb/permutations
          (concat segments empty-spaces))))))))