public class MaxPointsLine {
        public int maxPoints(int[][] points) {
            int ans = 1;
            for (int i = 0; i < points.length; i++) {
                ans = Math.max(countPoint(i, points), ans);
            }
            return ans;
        }


        public int countPoint(int index, int[][] points) {
            int ans = 0;
            HashMap<Double, HashMap<Double, Integer>> slopeCount = new HashMap<>();
            for (int i = index + 1; i < points.length; i++) {
                double slope;
                double intercept;
                if (points[index][0] == points[i][0]) {
                    slope = 20001;
                    intercept = points[i][0];
                } else {
                    slope = (double) (points[index][1] - points[i][1]) / (points[index][0] - points[i][0]);
                    intercept = (double) Math.round((points[i][1] - slope * points[i][0]) * 10000) / 10000;
                    if (slope == -0) {
                        slope = 0;
                    }
                }
                HashMap<Double, Integer> interceptMap = slopeCount.get(intercept);
                if (interceptMap == null) {
                    interceptMap = new HashMap<>();
                    slopeCount.put(intercept, interceptMap);
                }
                Integer pastVal = interceptMap.get(slope);
                if (pastVal == null) {
                    pastVal = 0;
                }
                pastVal++;
                interceptMap.put(slope, pastVal);
                if (pastVal > ans) {
                    ans = pastVal;
                }
            }
            return ans + 1;
        }
    }
}