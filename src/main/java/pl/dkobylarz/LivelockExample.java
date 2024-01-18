package pl.dkobylarz;

class LivelockExample {
    public static void main(String[] args) {
        final Person person1 = new Person("Alice");
        final Person person2 = new Person("Bob");

        final BookLending bookLending = new BookLending(person1);

        new Thread(() -> person1.lendBookWith(bookLending, person2)).start();
        new Thread(() -> person2.lendBookWith(bookLending, person1)).start();
    }

    static class BookLending {
        private Person currentHolder;

        public BookLending(Person p) {
            currentHolder = p;
        }

        public synchronized void setCurrentHolder(Person p) {
            currentHolder = p;
        }

        public synchronized void lendBook() {
            System.out.printf("%s lent the book!", currentHolder.name);
        }
    }

    static class Person {
        private final String name;
        private boolean wantsToRead;

        public Person(String name) {
            this.name = name;
            wantsToRead = true;
        }

        public String getName() {
            return name;
        }

        public boolean wantsToRead() {
            return wantsToRead;
        }

        public void lendBookWith(BookLending lending, Person person) {
            while (wantsToRead) {
                if (lending.currentHolder != this) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        continue;
                    }
                    continue;
                }

                if (person.wantsToRead()) {
                    System.out.printf(
                            "%s: You read first, %s!%n",
                            name, person.getName());
                    lending.setCurrentHolder(person);
                    continue;
                }

                lending.lendBook();
                wantsToRead = false;
                System.out.printf(
                        "%s: I have read the book, %s!%n",
                        name, person.getName());
                lending.setCurrentHolder(person);
            }
        }
    }
}
