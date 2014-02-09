(ns cross-me-spec
  (:require [speclj.core :refer :all]
            [cross-me    :refer :all]))

(describe "cross me"
  (context "builds a grid"
    (it "solves a row with only one extra space"
      (let [p1 [nil "x" nil "x" "x"]
            p2 ["x" nil nil "x" "x"]
            p3 ["x" nil "x" "x" nil]
            ps (vector p1 p2 p3)]
      (should== ps (row-possibilities [nil nil nil nil nil] [1 2]))))

    (it "solves a row with two extra spaces"
      (let [p1 [nil nil "x" "x" nil "x" "x" "x"]
            p2 ["x" "x" nil "x" "x" "x" nil nil]
            p3 [nil "x" "x" nil nil "x" "x" "x"]
            p4 ["x" "x" nil nil nil "x" "x" "x"]
            p5 [nil "x" "x" nil "x" "x" "x" nil]
            p6 ["x" "x" nil nil "x" "x" "x" nil]
            ps (vector p1 p2 p3 p4 p5 p6)]
      (should== ps (row-possibilities [nil nil nil nil nil nil nil nil] [2 3]))))

    (it "solves a row with two extra spaces"
      (let [p1 [nil "x" nil "x" "x" nil "x"]
            p2 ["x" nil "x" "x" nil "x" nil]
            p3 ["x" nil nil "x" "x" nil "x"]
            p4 ["x" nil "x" "x" nil nil "x"]
            ps (vector p1 p2 p3 p4)]
      (should== ps (row-possibilities [nil nil nil nil nil nil nil] [1 2 1]))))

    (it "returns true when there are nils between each segment"
      (should (nils-between-segments? [["x"] nil ["x" "x"] nil ["x"]])))

    (it "returns true when there are nils between each segment"
      (should (nils-between-segments? [["x"] nil nil ["x" "x"] nil nil ["x"]])))

    (it "returns true when there are nils between each segment"
      (should (nils-between-segments? [nil ["x"] nil ["x" "x"] nil ["x"] nil])))

    (it "returns true when there are nils between each segment"
      (should-not (nils-between-segments? [["x"] ["x" "x"] nil ["x"]])))
))