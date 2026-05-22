class CO2_Code {

    long[] tree;      // max value at each node
    long[] lazy;      // pending add for each node
    int n;

    // Constructor
    CO2_Code(int n) {

        this.n = n;

        tree = new long[4 * n];

        lazy = new long[4 * n];
    }

    // Push lazy value to children
    void pushDown(int node) {

        if (lazy[node] != 0) {

            tree[2 * node] += lazy[node];

            lazy[2 * node] += lazy[node];

            tree[2 * node + 1] += lazy[node];

            lazy[2 * node + 1] += lazy[node];

            lazy[node] = 0;
        }
    }

    // Range Update with Lazy Propagation
    void updateRange(int node, int lo, int hi,
                     int l, int r, long delta) {

        // No overlap
        if (r < lo || hi < l)
            return;

        // Full overlap
        if (l <= lo && hi <= r) {

            tree[node] += delta;

            lazy[node] += delta;

            return;
        }

        // Partial overlap
        pushDown(node);

        int mid = (lo + hi) / 2;

        updateRange(2 * node,
                    lo,
                    mid,
                    l,
                    r,
                    delta);

        updateRange(2 * node + 1,
                    mid + 1,
                    hi,
                    l,
                    r,
                    delta);

        tree[node] = Math.max(
                tree[2 * node],
                tree[2 * node + 1]
        );
    }

    public static void main(String[] args) {

        CO2_Code seg = new CO2_Code(16);

        // Operations from case study

        seg.updateRange(1, 0, 15, 3, 9, 5);

        seg.updateRange(1, 0, 15, 7, 14, 3);

        System.out.println(
            "Max after operation 2 = " + seg.tree[1]
        );

        seg.updateRange(1, 0, 15, 2, 6, 7);

        System.out.println(
            "Max after operation 4 = " + seg.tree[1]
        );
    }
}