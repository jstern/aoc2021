package aoc2021.day12;

import java.util.*;
import java.util.stream.Collectors;

public class CaveMap {
    static final String START = "start";
    static final String END = "end";

    private Map<String, Set<String>> graph = new HashMap<>();
    private boolean lax = false;

    public CaveMap(String input) {
        input.lines().forEach(ll -> {
            var ab = ll.split("-");
            connect(ab[0], ab[1]);
            connect(ab[1], ab[0]);
        });
    }

    public CaveMap(String input, boolean lax) {
        this(input);
        this.lax = lax;
    }

    void connect(String in, String out) {
        var outs = graph.getOrDefault(in, new HashSet<String>());
        if (!in.equals(END)) outs.add(out);
        graph.put(in, outs);
    }

    class Path {
        private Stack<String> path;
        private Set<String> spent;
        private Map<String,Integer> exempted;

        Path(String start) {
            this(new Stack<String>(), new HashSet<String>(), new HashMap<String,Integer>());
            this.path.push(start);
            this.mark(start);
        }

        private Path(Stack<String> path, Set<String> spent, Map<String,Integer> exempted) {
            this.path = path;
            this.spent = spent;
            this.exempted = exempted;
        }

        String head() {
            return this.path.peek();
        }

        boolean complete() {
            return END.equals(path.peek());
        }

        boolean mark(String cave) {
            if (cave.toLowerCase().equals(cave)) {
                if (!lax) {
                    spent.add(cave);
                    return false;
                }
                return allowOnce(cave);
            }
            return false;
        }

        boolean allowOnce(String cave) {
            // return true if we grant an exemption to a small cave
            assert (cave.toLowerCase().equals(cave));
            if (cave.equals(START) || cave.equals(END)) {
                spent.add(cave);
                return false;
            }

            var totalExemptions = exempted.values().stream().reduce(0, (a, b) -> a + b);
            var forCave = exempted.getOrDefault(cave, 0);
            exempted.put(cave, forCave + 1);
            if (totalExemptions > 0) {
                spent.add(cave);
                return false;
            } else {
                return true;
            }
        }

        List<Path> extend() {
            String cave = head();
            //System.out.println("extending " + this);
            var newPaths = new ArrayList<Path>();
            //System.out.println("all heads for " + cave + " " + graph.get(cave));
            var heads = graph.get(cave).stream().filter(c -> !this.spent.contains(c)).toList();
            //System.out.println("heads " + heads);
            for (var head : heads) {
                newPaths.addAll(this.plusHead(head));
            }
            //System.out.println("-> " + newPaths);
            return newPaths;
        }

        private List<Path> plusHead(String head) {
            var paths = new ArrayList<Path>();
            var newPath = fork();
            newPath.path.push(head);
            var exemptionGranted = newPath.mark(head);
            //System.out.println("adding " + newPath + " from " + this);
            paths.add(newPath);
            if (exemptionGranted) {
                // also return another path with the exemption not granted so it can be used by
                // a different small cave later
                var withoutExemption = newPath.fork();
                withoutExemption.exempted.put(head, 0);
                withoutExemption.spent.add(head);
                paths.add(withoutExemption);
                //System.out.println("  Also adding " + withoutExemption + " from " + this);
            }
            return paths;
        }

        private Path fork() {
            var p = new Stack<String>();
            p.addAll(path);
            var s = new HashSet<String>();
            s.addAll(spent);
            var e = new HashMap<String,Integer>();
            exempted.forEach((k, v) -> e.put(k, v));
            return new Path(p, s, e);
        }

        public String render() {
            return String.join(",", path);
        }

        public String toString() {
            return String.join(
                    ",",
                    path.stream().map(s -> s + "[" + exempted.getOrDefault(s, 0) + "]").toList()
            );
        }

        public int hashCode() {
            return toString().hashCode();
        }

        public boolean equals(Object other) {
            return (
                    null != other
                    && other instanceof Path
                    && ((Path)other).toString().equals(toString()));
        }
    }

    Set<String> explore() {
        Set<Path> complete = new HashSet<>();
        Set<Path> incomplete = new HashSet<>();

        incomplete.add(new Path(START));

        while (!incomplete.isEmpty()) {
            Set<Path> newIncomplete = new HashSet<>();
            for (var path : incomplete) {
                var extensions = path.extend();
                for (var ext : extensions) {
                    (ext.complete() ? complete : newIncomplete).add(ext);
                }
            }
            incomplete = newIncomplete;
            //System.out.println("COMPLETE " + complete);
            //System.out.println("INCOMPLETE " + incomplete);
            //System.out.println("");
        }

        return complete.stream().map(Path::render).collect(Collectors.toSet());
    }
}
