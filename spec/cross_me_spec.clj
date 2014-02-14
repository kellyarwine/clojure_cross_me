(ns cross-me-spec
  (:require [speclj.core :refer :all]
            [cross-me    :refer :all]))

(describe "cross me"
  (describe "blank?"
    (it "returns true if blank"
      (should (blank? ".")))

    (it "returns false if not blank"
      (should-not (blank? "x"))))

  (describe "not-blank?"
    (it "returns true if not blank"
      (should (not-blank? "x")))

    (it "returns false if blank"
      (should-not (not-blank? "."))))

  (describe "#row-possibilities"
    (it "solves a row with only one extra space"
      (let [p1 ["." "x" "." "x" "x"]
            p2 ["x" "." "." "x" "x"]
            p3 ["x" "." "x" "x" "."]
            ps (vector p1 p2 p3)]
      (should== ps (row-possibilities [nil nil nil nil nil] [1 2]))))

    (it "solves a solid row"
      (let [p1 ["x" "x" "x" "x" "x"]
            ps (vector p1)]
      (should== ps (row-possibilities [nil nil nil nil nil] [5]))))

    (it "solves part of a solid row"
      (let [p1 ["x" "x" "x" "x" "x" "." "." "."]
            p2 ["." "x" "x" "x" "x" "x" "." "."]
            p3 ["." "." "x" "x" "x" "x" "x" "."]
            p4 ["." "." "." "x" "x" "x" "x" "x"]
            ps (vector p1 p2 p3 p4)]
      (should== ps (row-possibilities [nil nil nil nil nil nil nil nil] [5]))))

    (it "solves a row with two extra spaces"
      (let [p1 ["." "." "x" "x" "." "x" "x" "x"]
            p2 ["x" "x" "." "x" "x" "x" "." "."]
            p3 ["." "x" "x" "." "." "x" "x" "x"]
            p4 ["x" "x" "." "." "." "x" "x" "x"]
            p5 ["." "x" "x" "." "x" "x" "x" "."]
            p6 ["x" "x" "." "." "x" "x" "x" "."]
            ps (vector p1 p2 p3 p4 p5 p6)]
      (should== ps (row-possibilities [nil nil nil nil nil nil nil nil] [2 3]))))

    (it "solves a row with two extra spaces"
      (let [p1 ["." "x" "." "x" "x" "." "x"]
            p2 ["x" "." "x" "x" "." "x" "."]
            p3 ["x" "." "." "x" "x" "." "x"]
            p4 ["x" "." "x" "x" "." "." "x"]
            ps (vector p1 p2 p3 p4)]
      (should== ps (row-possibilities [nil nil nil nil nil nil nil] [1 2 1]))))

    (it "solves a row with two extra spaces when row has hints"
      (let [p1 [nil "x" nil "x" "x" nil "x"]
            p2 ["x" nil "x" "x" nil "x" nil]
            p3 ["x" nil nil "x" "x" nil "x"]
            p4 ["x" nil "x" "x" nil nil "x"]
            ps (vector p1 p2 p3 p4)]
      (should== ps (row-possibilities [nil nil nil nil nil nil nil] [1 2 1])))))

    (describe "#segments-in-right-sequence?"
      (it "returns true when the segments are in the right order"
        (should (segments-in-right-sequence? '(["x"] "." ["x" "x"] ".") [["x"] ["x" "x"]])))

      (it "returns false when the segments are not in the right order"
        (should-not (segments-in-right-sequence? '(["x"] "." ["x" "x"] ".") [["x" "x"] ["x"]]))))

    (describe "#blanks-between-segments?"
      (it "returns true when there are blanks between each segment"
        (should (blanks-between-segments? [["x"] "." ["x" "x"] "." ["x"]])))

      (it "returns true when there are blanks between each segment"
        (should (blanks-between-segments? [["x"] "." "." ["x" "x"] "." "." ["x"]])))

      (it "returns true when there are blanks between each segment"
        (should (blanks-between-segments? ["." ["x"] "." ["x" "x"] "." ["x"] "."])))

      (it "returns true when there are blanks between each segment"
        (should-not (blanks-between-segments? [["x"] ["x" "x"] "." ["x"]])))

      (it "returns true when there is only one solid segment"
        (should (blanks-between-segments? [["x"]])))

      (it "returns true when there is two solid segments"
        (should (blanks-between-segments? [["x"] "." ["x"]])))

      (it "returns true when there is two solid segments"
        (should (blanks-between-segments? ["." ["x"] "."])))

      (it "returns true when there is two solid segments"
        (should (blanks-between-segments? ["." "." "." ["x"] "." "."]))))
)