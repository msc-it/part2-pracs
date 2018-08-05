% 1. Set input P and expected output (target) T
P = [-1.2 1.0];
T = [1.0 0.5];

% 2. Set weight and bias ranges
w_range = -1.2:0.1:1.2;
b_range = -1.2:0.1:1.2;

% 3. Create a linear neuron
net = newlind(P, T);

% 4. Simulate output for input P
A = sim(net, P);

% 5. Calculate error E = expected output T - actual output A
E = T - A;

% 6. Plot the error surface
ES = errsurf(P,T,w_range,b_range,'purelin'); % purelin is the transformation function to use
plotes(w_range,b_range,ES);

% 7. Plot the actual error as Sum of Squared Error on it
SSE = sumsqr(E);
plotep(net.IW{1,1}, net.b{1,1}, SSE);
