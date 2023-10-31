    import java.io.BufferedReader;
    import java.io.FileReader;
    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Scanner;

    public class SoftwareArchitectureDemo {

        public static void main(String[] args) {
            // 从文件中读取多行数据
            List<String> lines = readDataFromFile("E:/input.txt");

            if (lines.isEmpty()) {
                System.out.println("没有从文件中读取到数据。");
                return;
            }
            System.out.println("请选择判断是否构成三角形的软件体系风格");
            System.out.println("1.主程序-子程序软件体系结构");
            System.out.println("2.面向对象软件体系结构");
            System.out.println("3.事件系统软件体系结构");
            System.out.println("4.管道-过滤软件体系结构");
            System.out.println("请输入序号选择用何种风格实现");
            Scanner sc=new Scanner(System.in);
            int choice = sc.nextInt(); // 选择要演示的软件体系结构风格

            switch (choice) {
                case 1:
                    demonstrateMainSubProgramStyle(lines);
                    break;
                case 2:
                    demonstrateObjectOrientedStyle(lines);
                    break;
                case 3:
                    demonstrateEventSystemStyle(lines);
                    break;
                case 4:
                    demonstratePipeFilterStyle(lines);
                    break;
                default:
                    System.out.println("无效的选项。");
                    break;
            }
        }

        // 从文件中读取数据
        private static List<String> readDataFromFile(String filePath) {
            List<String> lines = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    lines.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return lines;
        }

        // 主程序-子程序风格
        private static void demonstrateMainSubProgramStyle(List<String> lines) {
            System.out.println("演示主程序-子程序风格：");
            System.out.println("调用判断能否构成三角形的子函数");
            System.out.println("");
            for (String line : lines) {

                String[] parts = line.split(" ");
                System.out.println("三边的长度分别为"+Double.parseDouble(parts[0])+"/"
                        +Double.parseDouble(parts[1])+"/"+Double.parseDouble(parts[2]));
                if (parts.length == 3) {
                    double side1 = Double.parseDouble(parts[0]);
                    double side2 = Double.parseDouble(parts[1]);
                    double side3 = Double.parseDouble(parts[2]);

                    boolean isTriangle = checkTriangle(side1, side2, side3);

                    if (isTriangle) {
                        System.out.println("可以构成三角形");
                    } else {
                        System.out.println("不能构成三角形");
                    }
                } else {
                    System.out.println("无效的数据行: " + line);
                }
            }
        }

        // 面向对象风格
        private static void demonstrateObjectOrientedStyle(List<String> lines) {
            System.out.println("演示面向对象风格：");
            System.out.println("构建一个三角类，包含构造方法和判定能否构成三角形的方法");
            for (String line : lines) {
                String[] parts = line.split(" ");
                System.out.println("三边的长度分别为 " + Double.parseDouble(parts[0]) + "/" + Double.parseDouble(parts[1]) + "/" + Double.parseDouble(parts[2]));
                if (parts.length == 3) {
                    double side1 = Double.parseDouble(parts[0]);
                    double side2 = Double.parseDouble(parts[1]);
                    double side3 = Double.parseDouble(parts[2]);

                    Triangle triangle = new Triangle(side1, side2, side3);

                    boolean isTriangle = triangle.isTriangle();

                    System.out.println(isTriangle ? "可以构成三角形" : "不能构成三角形");
                } else {
                    System.out.println("无效的数据行: " + line);
                }
            }
        }


        // 事件系统风格示例
        private static void demonstrateEventSystemStyle(List<String> lines) {
            System.out.println("演示事件系统风格：");
            for (String line : lines) {
                String[] parts = line.split(" ");
                System.out.println("三边的长度分别为 " + Double.parseDouble(parts[0]) + "/" + Double.parseDouble(parts[1]) + "/" + Double.parseDouble(parts[2]));
                if (parts.length == 3) {
                    double side1 = Double.parseDouble(parts[0]);
                    double side2 = Double.parseDouble(parts[1]);
                    double side3 = Double.parseDouble(parts[2]);

                    EventSystem eventSystem = new EventSystem();
                    eventSystem.triggerEvent(side1,side2,side3);
                    boolean isTriangle = eventSystem.checkTriangle(side1, side2, side3);

                    System.out.println(isTriangle ? "可以构成三角形" : "不能构成三角形");
                } else {
                    System.out.println("无效的数据行: " + line);
                }
            }
        }


        // 管道-过滤器风格
        private static void demonstratePipeFilterStyle(List<String> lines) {
            System.out.println("演示管道-过滤器风格：");
            for (String line : lines) {
                String[] parts = line.split(" ");
                System.out.println("三边的长度分别为 " + Double.parseDouble(parts[0]) + "/" + Double.parseDouble(parts[1]) + "/" + Double.parseDouble(parts[2]));
                if (parts.length == 3) {
                    double side1 = Double.parseDouble(parts[0]);
                    double side2 = Double.parseDouble(parts[1]);
                    double side3 = Double.parseDouble(parts[2]);

                    Pipe pipe = new Pipe(side1, side2, side3);
                    Filter filter1 = new Filter("Filter 1");
                    Filter filter2 = new Filter("Filter 2");

                    pipe.addFilter(filter1);
                    pipe.addFilter(filter2);

                    boolean result = pipe.process();
                    String r="";
                    if (result)
                        r="这三个数构成三角形";
                    else
                        r="这三个数不构成三角形";

                    System.out.println("处理后的结果: " + r);
                } else {
                    System.out.println("无效的数据行: " + line);
                }
            }
        }

        public static boolean checkTriangle(double side1, double side2, double side3) {
            // 判断是否能构成三角形的逻辑
            return side1 + side2 > side3 && side1 + side3 > side2 && side2 + side3 > side1;
        }
    }

    class Triangle {
        private double side1;
        private double side2;
        private double side3;

        public Triangle(double side1, double side2, double side3) {
            this.side1 = side1;
            this.side2 = side2;
            this.side3 = side3;
        }

        public boolean isTriangle() {
            return SoftwareArchitectureDemo.checkTriangle(side1, side2, side3);
        }
    }

    class EventSystem {
        private List<EventListener> listeners = new ArrayList<>();

        public void triggerEvent(double side1, double side2, double side3) {
            System.out.println("事件触发: 输入边长1 = " + side1 +
                    ", 输入边长2 = " + side2 +
                    ", 输入边长3 = " + side3);

            for (EventListener listener : listeners) {
                listener.handleEvent(side1, side2, side3);
            }
        }
        public static boolean checkTriangle(double side1, double side2, double side3) {
            // 判断是否能构成三角形的逻辑
            return side1 + side2 > side3 && side1 + side3 > side2 && side2 + side3 > side1;
        }
    }

    class EventListener {
        public void handleEvent(double side1, double side2, double side3) {
            boolean isTriangle = SoftwareArchitectureDemo.checkTriangle(side1, side2, side3);

            if (isTriangle) {
                System.out.println("可以构成三角形");
            } else {
                System.out.println("不能构成三角形");
            }
        }
    }

    class Pipe {
        private double side1;
        private double side2;
        private double side3;
        private List<Filter> filters = new ArrayList<>();

        public Pipe(double side1, double side2, double side3) {
            this.side1 = side1;
            this.side2 = side2;
            this.side3 = side3;
        }

        public void addFilter(Filter filter) {
            filters.add(filter);
        }

        public boolean process() {
            double data = side1 + side2 + side3;
            for (Filter filter : filters) {
                data = filter.run(data);
            }
            return side1 + side2 > side3 && side1 + side3 > side2 && side2 + side3 > side1;

        }
    }

    class Filter {
        private String name;

        public Filter(String name) {
            this.name = name;
        }

        public double run(double data) {
            System.out.println(name + " 正在处理数据...");
            // 这里可以定义不同的数据处理逻辑
            return data; // 这里示例中不进行实际处理
        }
    }
