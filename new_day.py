#! /usr/bin/env python3

from pathlib import Path
import sys

src = """package aoc2021.day{n};

import aoc2021.Solution;

public final class Day{n} implements Solution {{
    public final Object part1(String input) {{
        return "";
    }}

    public final Object part2(String input) {{
        return "";
    }}
}}
"""

tst = """package aoc2021.day{n};

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day{n}Tests {{

    @Test
    @DisplayName("day {n} something does something")
    @Disabled
    void something() {{
        assertEquals(true, false);
    }}
}}
"""


def start_day(n):
    src_dir = Path(f"src/main/java/aoc2021/day{n}")
    tst_dir = Path(f"src/test/java/aoc2021/day{n}")

    create(src_dir, f"Day{n}.java", src, n)
    create(tst_dir, f"Day{n}Tests.java", tst, n)


def create(path, out, tpl, n):
    if path.is_file():
        print(f"{path} exists")
        return

    path.mkdir(parents=True)
    dest = path / out
    dest.write_text(tpl.format(n=n))
    print(f"Created {dest}")


if __name__ == "__main__":
    start_day(sys.argv[1])
