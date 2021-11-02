# 设计报告

### 启动阶段：关键问题回答

**a. What will a jumper do if the location in front of it is empty, but the location two cells in front contains a flower or a rock?**

答：如果jumper前面的位置是空的，但前面两个单元格的位置包含一朵花或一块石头，jumper会调用turn方法向右转45°。

**b. What will a jumper do if the location two cells in front of the jumper is out of the grid?**

答：如果jumper前面的两个单元格的位置超出grid，jumper会调用turn方法向右转45°。

**c. What will a jumper do if it is facing an edge of the grid?**

答：如果jumper面对grid的边缘，jumper会调用turn方法向右转45°。

**d. What will a jumper do if another actor (not a flower or a rock) is in the cell that is two cells in front of the jumper?**

答：如果另一个actor（非花或者石头）在jumper前面两个单元格的单元格里，jumper会调用turn方法向右转45°。

**e. What will a jumper do if it encounters another jumper in its path?**

答：当jumper碰到另一个jumper在其路径上时，如果jumper前面两个单位格为网格内的没有actor（包括石头或花）或另一个jumper的单元格，那么jumper会跳到那个单元格，如果jumper前面两个单元格为网格外或单元格内有actor（包括石头或花）或另一个jumper，那么jumper会调用turn方法向右转45°。

**f. Are there any other tests the jumper needs to make?**

答：需要。比如当jumper前面一个单元格中有一个actor（包括石头或花）时，如果jumper前面两个单位格为网格内的没有花或石头或另一个jumper的单元格，那么jumper会跳到那个单元格，如果jumper前面两个单元格为网格外或单元格内有花或石头或另一个jumper，那么jumper会调用turn方法向右转45°。

### 总结跳跃动作

|情况|跳跃动作|
|----|-------|
|如果jumper前面的位置是空的，但前面两个单元格的位置，包含一朵花或一块石头|jumper会调用turn方法向右转45°|
|如果jumper前面的两个单元格的位置超出grid|jumper会调用turn方法向右转45°|
|如果jumper面对grid的边缘|jumper会调用turn方法向右转45°|
|如果另一个actor（非花或者石头）在jumper前面两个单元格的单元格里|jumper会调用turn方法向右转45°|
|当jumper碰到另一个jumper在其路径上时，如果jumper前面两个单位格，为网格内的没有actor（包括石头或花）或另一个jumper的单元格|jumper会跳到那个单元格|
|当jumper碰到另一个jumper在其路径上时，如果jumper前面两个单元格，为网格外或单元格内有actor（包括石头或花）或另一个jumper|jumper会调用turn方法向右转45°|
|当jumper前面一个单元格中有一个actor（包括石头或花）时，如果jumper前面两个单位格，为网格内的没有actor（包括石头或花）或另一个jumper的单元格|jumper会跳到那个单元格|
|当jumper前面一个单元格中有一个actor（包括石头或花）时，如果jumper前面两个单元格，为网格外或单元格内有actor（包括石头或花）或另一个jumper|jumper会调用turn方法向右转45°|
