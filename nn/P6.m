% 1. Set input and target output
P = [0 1 2 3 4 5 6 7 8 9 10];
T = [0 1 2 3 4 3 2 1 2 3 4];

% 2. Build a feed forward neural network
net = newff([0 10], [5 1], {'tansig' 'purelin'}); 

% 3. Simulate the network with input P
%    and plot input vs target and input vs expected
Y = sim(net, P);
subplot(2,1,1)
plot(P, T, P, Y);

% 4. Train the network
net.trainParam.epochs = 40;
net = train(net, P, T);

% 5. Simulate the network with input P again, and plot it
Y = sim(net, P);
subplot(2,1,2)
plot(P, T, P, Y);
